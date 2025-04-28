-- V1__init_schema.sql

-- 1. Majors
CREATE TABLE majors (
    id  BIGSERIAL  PRIMARY KEY,
    name  VARCHAR(255),
    short_name  VARCHAR(255) UNIQUE
);

-- 2. Users (AppUser)
CREATE TABLE users (
    id  BIGSERIAL  PRIMARY KEY,
    name  VARCHAR(255),
    surname  VARCHAR(255),
    email  VARCHAR(255) UNIQUE,
    login  VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL
);

-- 3. Semesters
CREATE TABLE semesters (
    id  BIGSERIAL  PRIMARY KEY,
    code  VARCHAR(255),
    start_date  TIMESTAMP  NOT NULL,
    end_date  TIMESTAMP  NOT NULL,
    major_id  BIGINT  REFERENCES majors(id)
);

-- 4. Students
CREATE TABLE students (
    id  BIGSERIAL  PRIMARY KEY,
    name  VARCHAR(255),
    surname  VARCHAR(255),
    album_number  INTEGER  NOT NULL UNIQUE,
    major_id  BIGINT  REFERENCES majors(id),
    year  INTEGER  NOT NULL,
    student_status  VARCHAR(20)  NOT NULL DEFAULT 'AKTYWNY'
);

-- 5. Courses
CREATE TABLE courses (
    id  BIGSERIAL  PRIMARY KEY,
    name  VARCHAR(255),
    code  VARCHAR(255) NOT NULL UNIQUE,
    ects  SMALLINT
);

-- 6. Course Editions
CREATE TABLE course_editions (
    id  BIGSERIAL  PRIMARY KEY,
    course_id  BIGINT  NOT NULL REFERENCES courses(id),
    semester_id BIGINT  NOT NULL REFERENCES semesters(id),
    user_id  BIGINT  NOT NULL REFERENCES users(id)
);

-- 7. Course Parts
CREATE TABLE course_parts (
    id  BIGSERIAL  PRIMARY KEY,
    course_edition_id  BIGINT  NOT NULL REFERENCES course_editions(id),
    type  VARCHAR(20)  NOT NULL,
    description  VARCHAR(255),
    ects  SMALLINT
);

-- 8. Groups
CREATE TABLE groups (
    id  BIGSERIAL  PRIMARY KEY,
    course_part_id  BIGINT  REFERENCES course_parts(id),
    code  VARCHAR(255),
    user_id  BIGINT  REFERENCES users(id)
);

-- 9. Lessons
CREATE TABLE lessons (
    id  BIGSERIAL  PRIMARY KEY,
    group_id  BIGINT  REFERENCES groups(id),
    start_date  TIMESTAMP  NOT NULL,
    end_date  TIMESTAMP  NOT NULL,
    subject  VARCHAR(255),
    room  VARCHAR(255)
);

-- 10. Attendances
CREATE TABLE attendances (
    id  BIGSERIAL  PRIMARY KEY,
    lesson_id  BIGINT  REFERENCES lessons(id),
    student_id BIGINT  REFERENCES students(id),
    status  VARCHAR(20),
    user_id  BIGINT  REFERENCES users(id),
    date  TIMESTAMP  NOT NULL
);

-- 11. Students in Groups
CREATE TABLE students_in_groups (
    id  BIGSERIAL  PRIMARY KEY,
    student_id  BIGINT  REFERENCES students(id),
    group_id  BIGINT  REFERENCES groups(id)
);

-- 12. Grades
CREATE TABLE grades (
    id  BIGSERIAL  PRIMARY KEY,
    student_id BIGINT  REFERENCES students(id),
    score  DECIMAL(5,2) NOT NULL,
    comment  VARCHAR(255),
    user_id  BIGINT  REFERENCES users(id),
    date  TIMESTAMP  NOT NULL,
    group_id  BIGINT  REFERENCES groups(id)
);