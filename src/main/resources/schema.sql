-- DROP TABLE IF EXISTS modules;
-- DROP TABLE IF EXISTS assessments;

CREATE TABLE IF NOT EXISTS modules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    module_code TEXT,
    module_name TEXT
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS assessments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    module_id BIGINT NOT NULL,
    assessment_name TEXT,
    assessment_status TEXT
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS moderation_comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assessment_id BIGINT UNIQUE NOT NULL,
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
    dateCompleted TEXT,
    FOREIGN KEY (assessment_id) REFERENCES assessments(id)
) ENGINE=InnoDB;

CREATE TABLE md_form
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    if_notified BOOLEAN DEFAULT FALSE,
    if_checked  BOOLEAN DEFAULT FALSE,
    submit_date TEXT,
    times TEXT ,
    stu_num BIGINT NOT NULL,
    mark_dates TEXT,
    dead_line TEXT not null,
    summary TEXT,
    assessment_id BIGINT UNIQUE NOT NULL
);
