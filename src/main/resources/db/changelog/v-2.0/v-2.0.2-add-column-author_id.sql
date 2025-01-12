ALTER TABLE budget
    ADD COLUMN author_id bigint NULL;

ALTER TABLE budget
    ADD CONSTRAINT fk_budget_author_id FOREIGN KEY (author_id) REFERENCES author (id)
        ON DELETE SET NULL;