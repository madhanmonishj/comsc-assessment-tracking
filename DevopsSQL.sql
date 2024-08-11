CREATE SCHEMA IF NOT EXISTS AssessmentTracking;
USE AssessmentTracking;

CREATE TABLE IF NOT EXISTS modules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    module_code TEXT,
    module_name TEXT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS assessments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    module_id BIGINT NOT NULL,
    assessment_name TEXT,
    assessment_status TEXT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS moderation_comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assessment_id BIGINT NOT NULL,
    isLinkClear BOOLEAN DEFAULT FALSE,
    isActivityClear BOOLEAN DEFAULT FALSE,
    isCriteriaClear BOOLEAN DEFAULT FALSE,
    isClassClear BOOLEAN DEFAULT FALSE,
    isWorkDone BOOLEAN DEFAULT FALSE,
    isMistakeFree BOOLEAN DEFAULT FALSE,
    isRequirementsClear BOOLEAN DEFAULT FALSE,
    isSubArrange BOOLEAN DEFAULT FALSE,
    isPenaltySub BOOLEAN DEFAULT FALSE,
    isFeedReturn BOOLEAN DEFAULT FALSE,
    isMarkingPlan BOOLEAN DEFAULT FALSE,
    isScrutiny BOOLEAN DEFAULT FALSE,
    isModerated BOOLEAN DEFAULT FALSE,
    comments TEXT,
    dateCompleted DATE,
    tracker_id BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS md_form (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    if_notified BOOLEAN DEFAULT FALSE,
    if_checked BOOLEAN DEFAULT FALSE,
    submit_date TEXT,
    times TEXT,
    stu_num BIGINT NOT NULL,
    mark_dates TEXT,
    dead_line TEXT NOT NULL,
    summary TEXT,
    assessment_id BIGINT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userID BIGINT NULL,
    mail VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    role INT NULL,
    CONSTRAINT mail_unique UNIQUE (mail)  -- Corrected the constraint syntax
);

CREATE TABLE IF NOT EXISTS external_form (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assessmentId INT NOT NULL,
    userId INT NULL,
    assessmentStatus INT NULL,
    feedback VARCHAR(255) NULL,
    feedbackDate DATETIME NULL
);

INSERT INTO user_info (userID, mail, password, userName, role) 
VALUES (718738, 'google@gmail.com', '$2a$10$Wm3MaWOpdiGedV26kbsYR.Bm9mto0d5qCTYQQkDfQNCt.IQVM1c1K', '3333', 0);

CREATE TABLE IF NOT EXISTS module_leader_form (
    assessmentId INT,
    userId INT,
    feedback TEXT,
    editsMade BOOLEAN,
    completedDate DATETIME,
    panelNotify BOOLEAN,
    notifyDate DATETIME
);

CREATE TABLE IF NOT EXISTS panel_feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    considered BOOLEAN,
    consideration_date VARCHAR(225),
    feedback VARCHAR(225),
    notified_moderator_setter BOOLEAN,
    notification_date VARCHAR(225),
    notified_moduleLeader BOOLEAN,
    moduleLeader_notification_date VARCHAR(225),
    assessment_status VARCHAR(225),
    user_id BIGINT,
    assessment_id INT
);

CREATE TABLE IF NOT EXISTS file_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assessment_id INT NULL,
    user_id BIGINT NULL,
    url VARCHAR(255) NULL,
    file_name VARCHAR(255) NULL,
    upload_time TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS response_to_external_examiner (
    feedback TEXT,
    editsMade BOOLEAN,
    dateCompleted DATE,
    assessmentReady BOOLEAN,
    dateSent DATE
);

CREATE TABLE IF NOT EXISTS external_examiner_feedback_monitoring (
    commentsPassed BOOLEAN,
    dateCompleted DATE,
    documentPassed BOOLEAN,
    dateSent DATE
);

CREATE TABLE IF NOT EXISTS tracker (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    assessment_id BIGINT(20) NOT NULL,
    module_leader_id BIGINT(20),
    panel_staff_id BIGINT(20),
    internal_moderator_id BIGINT(20),
    external_moderator_id BIGINT(20),
    assessment_status TEXT,
    uploaded_assessment_name TEXT,
    user_id BIGINT(20),
    PRIMARY KEY (id),
    INDEX (assessment_id),
    FOREIGN KEY (assessment_id) REFERENCES assessments(id)
);

CREATE TABLE IF NOT EXISTS assessments1 (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    module_id BIGINT(20) NOT NULL,
    assessment_name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tracker1 (
    id INT(11) NOT NULL AUTO_INCREMENT,
    assessment_id BIGINT(20) NOT NULL,
    module_leader_id BIGINT(20),
    panel_staff_id BIGINT(20),
    internal_moderator_id BIGINT(20),
    external_moderator_id BIGINT(20),
    assessment_status TEXT,
    uploaded_assessment_name TEXT,
    user_id BIGINT(20),
    PRIMARY KEY (id),
    INDEX (assessment_id),
    FOREIGN KEY (assessment_id) REFERENCES assessments1(id)
);