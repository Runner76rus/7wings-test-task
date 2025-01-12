CREATE TABLE budget
(
    id     bigint GENERATED ALWAYS AS IDENTITY primary key,
    year   int  not null,
    month  int  not null,
    amount int  not null,
    type   varchar(32) not null
);