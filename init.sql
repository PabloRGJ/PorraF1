-- Initial database setup
-- Hibernate will auto-create tables via ddl-auto=update

-- Optional: seed data
-- This runs after Hibernate creates the schema

DO $$
BEGIN
    -- Wait a moment for schema to be created by Hibernate on first run
    -- You can add seed data here after the first startup
    RAISE NOTICE 'Database initialized successfully';
END $$;
