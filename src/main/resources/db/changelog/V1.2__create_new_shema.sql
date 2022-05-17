BEGIN;

CREATE TABLE service.application_details
(
    id         BIGINT PRIMARY KEY,
    app_key    VARCHAR(255) NOT NULL,
    app_secret VARCHAR(255) NOT NULL,
    active     BIT          NOT NULL default '1',
    route      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created    DATE         NOT NULL,
    updated    DATE         NOT NULL,
    created_by VARCHAR(255) NOT NULL
);

COMMIT;
