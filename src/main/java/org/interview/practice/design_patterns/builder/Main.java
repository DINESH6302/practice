package org.interview.practice.design_patterns.builder;

public class Main {
    public static void main(String[] args) {
        Student.Builder bulder = new Student.Builder();

        Student student1 = bulder
                .setName("dinesh")
                .setAge(23)
                .setEmail("dinesh@gmail.com")
                .setPhone(987654321)
                .build();

        Student student2 = bulder
                .setName("kumar")
                .setAge(23)
                .setEmail("kumar@gmail.com")
                .setFatherName("siva")
                .build();
    }
}
