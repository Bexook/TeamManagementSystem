BEGIN;

CREATE TABLE app_user
(
    id                     BIGINT PRIMARY KEY,
    email                  VARCHAR(500) UNIQUE NOT NULL,
    password               VARCHAR(500)        NOT NULL,
    is_enable              BOOLEAN             NOT NULL default false,
    is_active              BOOLEAN             NOT NULL default false,
    is_expired             BOOLEAN             NOT NULL default false,
    is_credentials_expired BOOLEAN             NOT NULL default false,
    created_at             DATE                NOT NULL,
    modified_at            DATE,
    is_email_verified      BOOLEAN             NOT NULL default false,
    role                   VARCHAR(20)         NOT NULL
);


CREATE TABLE app_user_audit
(
    id                 BIGINT PRIMARY KEY,
    user_id            BIGINT        NOT NULL REFERENCES app_user (id),
    changed_properties VARCHAR(5000) NOT NULL,
    modified_by        BIGINT        NOT NULL REFERENCES app_user (id)
);

CREATE TABLE time_log
(
    id                BIGINT PRIMARY KEY,
    date              DATE        NOT NULL,
    working_time      FLOAT       NOT NULL,
    calendar_day_type VARCHAR(80) NOT NULL default 'WORKING',
    created_at        DATE        NOT NULL,
    modified_at       DATE        NOT NULL,
    created_by        BIGINT      NOT NULL REFERENCES app_user (id)
);


CREATE TABLE team_member
(
    id               BIGINT PRIMARY KEY,
    username         VARCHAR(500) NOT NULL,
    user_id          BIGINT       NOT NULL REFERENCES app_user (id),
    team_member_role VARCHAR(80)  NOT NULL default 'NOT_ASSIGNED'
);



CREATE TABLE active_tokens
(
    id        BIGINT PRIMARY KEY,
    jwt_token VARCHAR(1000) NOT NULL,
    user_id   BIGINT        NOT NULL REFERENCES app_user (id)
);

CREATE TABLE IF NOT EXISTS change_request
(
    id                   BIGINT PRIMARY KEY,
    created_by           BIGINT        NOT NULL REFERENCES app_user (id),
    approver_role        VARCHAR(50)   NOT NULL,
    state                VARCHAR(100)  NOT NULL,
    operation_type       VARCHAR(100)  NOT NULL,
    current_object_state VARCHAR(5000) NOT NULL,
    new_object_state     VARCHAR(5000) NOT NULL,
    domain_class         VARCHAR(256)  NOT NULL,
    created_at           DATE          NOT NULL,
    modified_at          DATE          NOT NULL,
    is_relevant          BOOLEAN       NOT NULL default true,
    object_repo          VARCHAR(256)  NOT NULL
);


CREATE TABLE IF NOT EXISTS change_request_comment
(
    id                BIGINT PRIMARY KEY,
    change_request_id BIGINT        NOT NULL REFERENCES change_request (id),
    comment           VARCHAR(5000) NOT NULL,
    comment_by        BIGINT        NOT NULL REFERENCES app_user (id),
    created_at        DATE          NOT NULL,
    modified_at       DATE,
    is_relevant       BOOLEAN       NOT NULL default true
);

CREATE TABLE IF NOT EXISTS user_message
(
    id           BIGINT PRIMARY KEY,
    sender_id    BIGINT        NOT NULL REFERENCES app_user (id),
    receiver_id  BIGINT        NOT NULL REFERENCES app_user (id),
    sending_time DATE          NOT NULL,
    is_read      BOOLEAN       NOT NULL default false,
    read_time    BIT,
    body         VARCHAR(5000) NOT NULL,
    deleted      BOOLEAN       NOT NULL default false
);

COMMIT;