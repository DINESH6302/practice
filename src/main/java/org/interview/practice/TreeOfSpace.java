package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.*;

class TreeNode {
    final String name;
    final TreeNode parent;
    final List<TreeNode> children;
    final ReentrantReadWriteLock nodeLock = new ReentrantReadWriteLock();
    volatile boolean isLocked;
    volatile int lockedBy;
    final Set<TreeNode> lockedDescendants = ConcurrentHashMap.newKeySet();

    public TreeNode(String name, TreeNode parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
}

public class TreeOfSpace {
    private Map<String, TreeNode> nodeMap;
    private TreeNode root;
    private int m;
    private ThreadLocal<Set<TreeNode>> heldLocks;

    public TreeOfSpace(int m) {
        this.m = m;
        nodeMap = new ConcurrentHashMap<>();
        heldLocks = ThreadLocal.withInitial(HashSet::new);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int m = scanner.nextInt();
        int Q = scanner.nextInt();
        scanner.nextLine();

        String[] nodeNames = new String[N];
        for (int i = 0; i < N; i++) {
            nodeNames[i] = scanner.nextLine().trim();
        }

        TreeOfSpace tree = new TreeOfSpace(m);
        tree.buildTree(nodeNames);

        for (int i = 0; i < Q; i++) {
            String[] query = scanner.nextLine().trim().split(" ");
            int operationType = Integer.parseInt(query[0]);
            String nodeName = query[1];
            int userId = Integer.parseInt(query[2]);

            boolean result = false;
            switch (operationType) {
                case 1:
                    result = tree.lock(nodeName, userId);
                    break;
                case 2:
                    result = tree.unlock(nodeName, userId);
                    break;
                case 3:
                    result = tree.upgradeLock(nodeName, userId);
                    break;
            }
            System.out.println(result);
        }

        scanner.close();
    }

    public void buildTree(String[] nodeNames) {
        if (nodeNames.length == 0) throw new IllegalArgumentException("Empty tree");

        root = new TreeNode(nodeNames[0], null);
        nodeMap.put(nodeNames[0], root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while (!queue.isEmpty() && index < nodeNames.length) {
            TreeNode current = queue.poll();

            for (int i = 0; i < m && index < nodeNames.length; i++) {
                TreeNode child = new TreeNode(nodeNames[index], current);
                current.children.add(child);
                nodeMap.put(nodeNames[index], child);
                queue.add(child);
                index++;
            }
        }
    }

    public boolean lock(String nodeName, int userId) {
        TreeNode node = nodeMap.get(nodeName);
        if (node == null) return false;

        node.nodeLock.writeLock().lock();
        heldLocks.get().add(node);

        if (node.isLocked) return false;

        if (isAncestorLocked(node.parent)) {
            releaseHeldLocks();
            return false;
        }

        if (!node.lockedDescendants.isEmpty()) {
            releaseHeldLocks();
            return false;
        }

        node.isLocked = true;
        node.lockedBy = userId;

        updateAncestorLockedDescendants(node, true);

        return true;
    }

    private boolean isAncestorLocked(TreeNode node) {
        TreeNode current = node;
        while (current != null) {
            current.nodeLock.readLock().lock();
            heldLocks.get().add(current);

            if (current.isLocked) {
                return true;
            }
            current = current.parent;
        }
        return false;
    }

    private void updateAncestorLockedDescendants(TreeNode node, boolean add) {
        TreeNode parent = node.parent;
        while (parent != null) {
            parent.nodeLock.writeLock().lock();
            heldLocks.get().add(parent);
            if (add) {
                parent.lockedDescendants.add(node);
            } else {
                parent.lockedDescendants.remove(node);
            }
            parent = parent.parent;
        }
    }

    public boolean unlock(String nodeName, int userId) {
        TreeNode node = nodeMap.get(nodeName);
        if (node == null) return false;

        node.nodeLock.writeLock().lock();
        try {
            heldLocks.get().add(node);

            if (!node.isLocked || node.lockedBy != userId) {
                return false;
            }

            node.isLocked = false;
            node.lockedBy = -1;

            updateAncestorLockedDescendants(node, false);

            return true;
        } finally {
            releaseHeldLocks();
        }
    }

    public boolean upgradeLock(String nodeName, int userId) {
        TreeNode node = nodeMap.get(nodeName);
        if (node == null) return false;

        node.nodeLock.writeLock().lock();
        try {
            heldLocks.get().add(node);

            if (node.isLocked || node.lockedDescendants.isEmpty()) {
                return false;
            }

            for (TreeNode descendant : node.lockedDescendants) {
                descendant.nodeLock.readLock().lock();
                heldLocks.get().add(descendant);
                if (descendant.lockedBy != userId) {
                    return false;
                }
            }

            for (TreeNode descendant : node.lockedDescendants) {
                descendant.nodeLock.writeLock().lock();
                heldLocks.get().add(descendant);
                if (!descendant.isLocked || descendant.lockedBy != userId) {
                    return false;
                }
                descendant.isLocked = false;
                descendant.lockedBy = -1;
            }
            node.isLocked = true;
            node.lockedBy = userId;

            for (TreeNode descendant : node.lockedDescendants) {
                updateAncestorLockedDescendants(descendant, false);
            }
            node.lockedDescendants.clear();

            return true;
        } finally {
            releaseHeldLocks();
        }
    }

    private void releaseHeldLocks() {
        Set<TreeNode> locks = heldLocks.get();
        for (TreeNode node : locks) {
            if (node.nodeLock.writeLock().isHeldByCurrentThread()) {
                node.nodeLock.writeLock().unlock();
            } else {
                node.nodeLock.readLock().unlock();
            }
        }
        locks.clear();
    }
}