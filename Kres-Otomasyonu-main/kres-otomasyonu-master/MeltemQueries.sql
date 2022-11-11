/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [classroom_course_id]
      ,[classroom_id]
  FROM [meltemDB].[dbo].[classroom_courses]

--INSERT INTO classroom_courses(classroom_course_id, classroom_id) VALUES (1, 1);


-- This snippet queries the branch course's contents with an id of 1.
SELECT branch_courses.branch_id, branch_courses.branch_name, teachers.teacher_id, teachers.teacher_name, teachers.teacher_lastname, teachers.teacher_phone, teachers.teacher_email FROM
(
	branch_courses
	INNER JOIN teachers on branch_courses.branch_teacher_id = teachers.teacher_id
);

select * from classrooms;

-- This snippet will deliver the student list of a branch course.
SELECT branch_attendance.branch_id, students.student_id, students.student_name, students.student_lastname, students.orientation_start, students.orientation_end FROM
(
	branch_attendance
	INNER JOIN students on students.student_id = branch_attendance.student_id
);


-- This snippet queries a classroom's complete info.
SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity 
FROM (
	classroom_courses
	INNER JOIN classrooms on classroom_courses.classroom_id = classrooms.classroom_id
	INNER JOIN courses on classroom_courses.classroom_course_id = courses.course_id
	INNER JOIN teachers on classroom_courses.classroom_teacher_id = teachers.teacher_id
);

-- This query gets all classroom and teacher info with in a course ID of 1.
SELECT classroom_courses.classroom_course_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity
FROM (
	classroom_courses
	INNER JOIN classrooms ON classroom_courses.classroom_id = classrooms.classroom_id
	INNER JOIN teachers ON classroom_courses.classroom_teacher_id  = teachers.teacher_id
) WHERE classroom_course_id = 1;

-- This query gets all students' info in a classroom course with ID 1.
SELECT classroom_attendance.course_id, courses.course_name, students.student_id, students.student_name, students.student_lastname, students.orientation_start, students.orientation_end
FROM (
	classroom_attendance
	INNER JOIN courses ON classroom_attendance.course_id = courses.course_id
	INNER JOIN students ON classroom_attendance.student_id  = students.student_id
) WHERE classroom_attendance.course_id = 1;

-- This query gets all students' and teachers' info in a classroom course with ID 1.
SELECT classroom_attendance.course_id, courses.course_name, teachers.teacher_name, teachers.teacher_lastname, teachers.teacher_phone, students.student_id, students.student_name, students.student_lastname
FROM (
	classroom_attendance
	INNER JOIN courses ON classroom_attendance.course_id = courses.course_id
	INNER JOIN students ON classroom_attendance.student_id  = students.student_id
	INNER JOIN teachers ON classroom_attendance.teacher_id = teachers.teacher_id
) WHERE classroom_attendance.course_id = 1;

select courses.course_classroom_id, courses.course_id, courses.course_name, teachers.teacher_name, teachers.teacher_lastname, teachers.teacher_phone, teachers.teacher_email from (
	courses
	INNER JOIN teachers ON courses.course_teacher_id = teachers.teacher_id
);


update courses
set course_name = 'Tarih' where course_id = 2;

update teachers
set teacher_name = 'John', teacher_lastname = 'Beckham', teacher_phone = '09504444', teacher_email = '1@bku.gvn'
where teacher_id = (select teacher_id from teachers where teacher_name = 'Jane' AND teacher_lastname = 'Beckham' AND teacher_email = '1@bku.gvn') ;

select * from courses;

select * from teachers;

select courses.course_classroom_id, courses.course_id, courses.course_name, teachers.teacher_name, teachers.teacher_lastname, teachers.teacher_phone, teachers.teacher_email from (
	courses
	INNER JOIN teachers ON courses.course_teacher_id = teachers.teacher_id
);

SELECT TOP 1 * FROM teachers WHERE teacher_name = 'John';

INSERT INTO courses values (1, 'Fen', 1);
SELECT TOP 1 * FROM teachers WHERE teacher_name = 'Jane';

INSERT INTO teachers VALUES ('jane', 'doers', '232', 'assadf', 2);

update classroom_courses
set classroom_course_id = 1;


-- This query gets all students that attend the branch course by the ID of 1.
SELECT branch_attendance.branch_id, branch_courses.branch_name, branch_attendance.student_id, students.student_name, students.student_lastname, students.orientation_start, students.orientation_end
FROM (
	branch_attendance
	INNER JOIN branch_courses ON branch_attendance.branch_id = branch_courses.branch_id
	INNER JOIN students ON branch_attendance.student_id = students.student_id
) WHERE branch_attendance.branch_id = 1;

SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity 
FROM (classroom_courses INNER JOIN classrooms on classroom_courses.classroom_id = classrooms.classroom_id INNER JOIN courses on classroom_courses.classroom_course_id = courses.course_id INNER JOIN teachers on classroom_courses.classroom_teacher_id = teachers.teacher_id );

SELECT classroom_attendance.course_id, courses.course_name, students.student_id, students.student_name, students.student_lastname, students.orientation_start, students.orientation_end
FROM (
	classroom_attendance
	INNER JOIN courses ON classroom_attendance.course_id = courses.course_id
	INNER JOIN students ON classroom_attendance.student_id  = students.student_id
) WHERE classroom_attendance.course_id = 1;