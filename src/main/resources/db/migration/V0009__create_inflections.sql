CREATE TABLE IF NOT EXISTS verbs_infl
(
    vi_id serial,
    vi_vb_inf bigint,
    vi_name   varchar,
    vi_name_nek varchar,
    vi_en varchar,
    vi_ru varchar,
    vi_es varchar,
    vi_trans_en varchar,
    vi_trans_ru varchar,
    vi_gender varchar,
    vi_number varchar,
    vi_person varchar,
    vi_pronoun varchar,
    vi_root varchar,
    vi_form varchar,
    vi_actpass varchar,
    vi_binyan varchar,
    vi_transla_en varchar
);

CREATE INDEX vi_name ON verbs_infl USING gin(vi_name gin_trgm_ops);
CREATE INDEX vi_en ON verbs_infl USING gin(vi_en gin_trgm_ops);
CREATE INDEX vi_pronoun ON verbs_infl USING gin(vi_pronoun gin_trgm_ops);

ALTER TABLE verbs ADD PRIMARY KEY (vb_id);
ALTER TABLE verbs_infl
    ADD CONSTRAINT fk_infl_verbs FOREIGN KEY (vi_vb_inf) REFERENCES verbs (vb_id);

