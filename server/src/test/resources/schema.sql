CREATE OR REPLACE FUNCTION truncate_tables
(scheme in VARCHAR) RETURNS void AS '
DECLARE
    statements CURSOR FOR
    SELECT tablename FROM pg_tables
    WHERE schemaname = scheme AND (tablename NOT LIKE ''%pg_'' AND tablename NOT LIKE ''%sql_'');
BEGIN
    FOR stmt IN statements LOOP
    EXECUTE ''TRUNCATE TABLE '' || quote_ident(stmt.tablename) || '' CASCADE;'';
    END LOOP;
END;
' LANGUAGE plpgsql;

