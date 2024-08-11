import { groupAssessmentsByModule } from './utils.js';

// Function to handle button click events
function processClick(e, assessment) {
    const selectedIndex = e.currentTarget.textContent;

    const {
        avl_id = 0,
        mod_panel_com_id = 0,
        tracker_id
    } = assessment;

    const statusMap = {
        "1": "public/availabilityForm",
        "2": "moderationComments",
        "3": "rsInterModel",
        "4": "public/panelStaff",
        "5": "public/respMod",
        "6": "externalpage/form",
    }

    const urlParams = {
        "1": `id=${tracker_id}&avl_id=${avl_id}`,
        "2": `id=${tracker_id}&modPanC=${mod_panel_com_id}`,
        "3": `id=${tracker_id}`,
        "4": `id=${tracker_id}`,
        "5": `id=${tracker_id}`,
        "6": `id=${tracker_id}`,
    }

    window.location.href = `http://localhost:8080/${statusMap[selectedIndex]}?${urlParams[selectedIndex]}`;
}

// Function to append buttons and spans to a given buttonsDiv based on a processList
function buttonsDivAppend(buttonsDiv, assessment, assessmentDiv) {
    // List of processes
    const processList = [
        "Assessment Availability",
        "Internal Moderation Comments",
        "Response to Internal Moderation",
        "Moderation Panel Comments",
        "Response to Moderation Panel",
        "External examiner feedback"
    ];

    const statusARR = [
        "AS_AVL",
        "IN_MOD_C",
        "RSP_IN_MOD",
        "MOD_PAN_C",
        "RSP_MOD_P",
        "EXT_EXAM_FEED",
        "RSP_EXAM_FEED"
    ]

    const thresholdIndex = statusARR.findIndex(val => val === assessment.assessment_status);
    const labelArr = [];


    processList.forEach((process, index) => {

        // Create a button element with specified attributes
        const buttonClass = index < thresholdIndex
            ? "bg-success"
            : "bg-primary";
        const spanClass = index < thresholdIndex && !(index === processList.length - 1)
            ? "bg-success"
            : "bg-primary";

        const button = $("<button>", {
            class: `btn ${buttonClass} text-white btn-sm rounded-pill`,
            style: "width: 2.6rem; height: 2rem",
            click: e => { processClick(e, assessment); },
            "data-toggle": "tooltip",
            title: process,
            text: index + 1,
        });

        const label = $("<div>", {
            class: `label-text label_${index + 1}`,
            text: process,
        });

        labelArr.push(label);
        // Create a span element with specified attributes
        let span = $("<span>", {
            class: `${spanClass} w-25 rounded mt-auto mb-auto me-1 ms-1`,
            style: "height: 0.2rem",
        });

        // If it's the last iteration, set span to an empty string (no separator)
        if (index === processList.length - 1) {
            span = "";
        }

        buttonsDiv.append(button, span);
    });

    labelArr.forEach(el => {
        assessmentDiv.find('#buttons-container1').append(el);
    })
}

const getUsers = () => {
    $.ajax({
        url: '/Users/lists',
        type: 'GET',
        dataType: 'json',
        success: function (jsonData) {
            localStorage.setItem('usersList', JSON.stringify(jsonData));
        }
    });
}

const isUserCheck = (assessment, usersList) => {
    const userID = decodeURIComponent(new URLSearchParams(window.location.search).get("userId")) || 0

    const isPanel = assessment.panel_staff_id == userID;
    const isInternal = assessment.internal_moderator_id == userID;
    const isExternal = assessment.external_moderator_id == userID;
    const isModuleLeader = assessment.module_leader_id == userID;
    const user = usersList.find(val => val.id == userID) || [];
    const isAdmin = user.assignedRoles == 'psStaff' || user.assignedRoles == 'ModLeader';

    return isAdmin || isPanel || isInternal || isExternal || isModuleLeader;
}

$.ajax({
    url: '/tracking/lists',
    type: 'GET',
    dataType: 'json',
    success: function (jsonData) {
        // Get grouped Assessments
        const groupedAssessments = groupAssessmentsByModule(jsonData);
        // Load the assessment template
        getUsers();
        $.get('html/assessmentTemplate.html', function (template) {
            function createAssignmentElements(moduleCode, assessments) {
                // Create a module container div and clone the template
                const usersList = JSON.parse(localStorage.getItem('usersList'));;
                const moduleDiv = $('<div>').addClass('module-container').removeClass('d-none');
                const templateClone = $(template).clone();

                // Append an h3 element displaying the module code and name
                moduleDiv.append('<h3/>');
                moduleDiv.find('h3').text(`Module: ${moduleCode} - ${assessments[0].module_name}`);

                assessments.forEach((assessment) => {
                    const {
                        uploaded_assessment_name,
                        module_id,
                        assessment_id
                    } = assessment;

                    if (!isUserCheck(assessment, usersList)) {
                        return;
                    }

                    // Clone the assessment template so the current DOM wont get affected
                    const assessmentDiv = templateClone.find('.assessment').clone();
                    assessmentDiv.find('.assessmemtName').append(`<span><b>${uploaded_assessment_name}:<b></span>`);
                    assessmentDiv.find('#buttons-container').addClass(`progress_${module_id}_${assessment_id}`);

                    const buttonsDiv = assessmentDiv.find('#buttons-container');
                    buttonsDivAppend(buttonsDiv, assessment, assessmentDiv);

                    // Append the assessmentDiv to the moduleDiv
                    moduleDiv.append(assessmentDiv);
                });

                // Append the moduleDiv to the '#assessmentsContainer' in the DOM
                $('#assessmentsContainer').append(moduleDiv);
            }

            for (const moduleCode in groupedAssessments) {
                if (groupedAssessments.hasOwnProperty(moduleCode)) {
                    const assessments = groupedAssessments[moduleCode];
                    createAssignmentElements(moduleCode, assessments);
                }
            }
        });
    },
    // Error Handling for failed AJAX requests
    error: function (jqXHR, textStatus, errorThrown) {
        console.error('Error fetching JSON data:', textStatus, errorThrown);
    }
});
