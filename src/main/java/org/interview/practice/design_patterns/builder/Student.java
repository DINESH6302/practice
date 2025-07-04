package org.interview.practice.design_patterns.builder;

public class Student {
    private String name;
    private int age;
    private long phone;
    private String email;
    private String address;
    private String fatherName;
    private String motherName;

    private Student(Builder builder) {
        name = builder.name;
        age = builder.age;
        phone = builder.age;;
        email = builder.email;
        address = builder.address;
        fatherName = builder.fatherName;
        motherName = builder.motherName;
    }

    public static class Builder {
        private String name;
        private int age;
        private long phone;
        private String email;
        private String address;
        private String fatherName;
        private String motherName;

        public Student build() {
            return new Student(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setPhone(long phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setFatherName(String fatherName) {
            this.fatherName = fatherName;
            return this;
        }

        public Builder setMotherName(String motherName) {
            this.motherName = motherName;
            return this;
        }
    }
}
