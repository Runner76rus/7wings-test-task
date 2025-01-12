CREATE TABLE author
(
    id         bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name  varchar(128) NOT NULL,
    created_at timestamp(0) DEFAULT current_timestamp(0)
)