create TABLE TASKS(
    id serial primary key,
    title varchar(255),
    author varchar(255),
    created_date bigint
)