-- Clear existing data
-- DELETE FROM modules;
-- DELETE FROM assessments;

-- Insert data into 'modules' table
INSERT INTO modules (module_code, module_name)
VALUES
    ('CMT145Q', 'Agile Software Development'),
    ('CMY2553', 'Programming Principles'),
    ('CMD32GW', 'Web Development');

-- Insert data into 'assessments' table
INSERT INTO assessments (module_id, assessment_name, assessment_status)
VALUES
    (1, '23022234_Portfolio_Report', 'AS_AVL'),
    (1, '23036376_Portfolio_Report', 'MOD_PAN_C'),
    (1, '58564363_Portfolio_Report', 'AS_AVL'),
    (2, '25235235_Bank_Application_Jar', 'AS_AVL'),
    (2, '33463646_Bank_Application_Jar', 'RSP_IN_MOD'),
    (2, '79443548_Bank_Application_Jar', 'IN_MOD_C'),
    (3, '64367453_Agile_Reflection_Report', 'AS_AVL'),
    (3, '23523553_Agile_Reflection_Report', 'RSP_MOD_P'),
    (3, '23647866_Agile_Reflection_Report', 'AS_AVL');