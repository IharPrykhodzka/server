create TABLE TASKS(
    id serial primary key,
    user_id integer,
    title varchar(255),
    content varchar(255),
    created_date varchar(255)
)