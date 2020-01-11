CREATE TABLE IF NOT EXISTS verbs
(
    vb_id serial,
    vb_name   varchar,
    vb_name_nek varchar,
    vb_binyan varchar,
    vb_type   varchar,
    vb_root varchar,
    vb_gerund varchar,
    vb_main varchar,
    vb_en varchar,
    vb_ru varchar,
    vb_es varchar,
    vb_trans_en varchar,
    vb_trans_ru varchar
);
CREATE INDEX vb_name ON verbs USING gin(vb_name gin_trgm_ops);
CREATE INDEX vb_binyan ON verbs USING gin(vb_binyan gin_trgm_ops);
CREATE INDEX vb_main ON verbs USING gin(vb_main gin_trgm_ops);


