// Function to group Assessments by module code
const groupAssessmentsByModule = (assessments) => {
    const groupedAssessments = {};
    assessments.forEach(assignment => {
        const moduleCode = assignment.module_code;

        if (!groupedAssessments[moduleCode]) {
            groupedAssessments[moduleCode] = [];
        }
        groupedAssessments[moduleCode].push(assignment);
    });

    return groupedAssessments;
}

export { 
    groupAssessmentsByModule 
};