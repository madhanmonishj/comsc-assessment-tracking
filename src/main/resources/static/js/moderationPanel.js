//Checkbox array of objects
const tablePopulationList = [
    { id: "isLinkClear", desc: "The link between learning outcomes and assessment activities is clear" },
    { id: "isActivityClear", desc: "The activities to be completed as part of the assessment are clear and appropriate" },
    { id: "isCriteriaClear", desc: "The criteria to be used to assign marks to parts of the assessment are clear and appropriate" },
    { id: "isClassClear", desc: "(If appropriate for the type of assessment) The requirements for each grade classification are clear" },
    { id: "isWorkDone", desc: "The amount of work being undertaken is fair given the relative weighting of the assessment to the overall module" },
    { id: "isMistakeFree", desc: "The assessment is free from mistakes" },
    { id: "isRequirementsClear", desc: "(If appropriate for the type of assessment) The requirements for each grade classification are clear" },
    { id: "isSubArrange", desc: "Submission arrangements are clearly stated" },
    { id: "isPenaltySub", desc: "Penalties for incorrect submissions are stated" },
    { id: "isFeedReturn", desc: "Feedback return date and method clearly stated" },
    { id: "isMarkingPlan", desc: "The marking plan is proportionate and achievable" }
];

//params obtained from url params
const assessmentId = decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0;
const modPanId = decodeURIComponent(new URLSearchParams(window.location.search).get("modPanC")) || 0;

// only fetch if it is an existing record
if (modPanId) {
    // ajax call to fetch comments
    $.ajax({
        type: 'GET',
        url: '/getComments',
        contentType: 'application/json',
        data: { assessmentId },
        success: function (comments) {
            //populate the data after getting from ajax
            tablePopulationList.forEach(function (el) {
                const isChecked = comments[0][el.id] || false;

                $(`#q${el.id}_yes`).prop('checked', isChecked);
                $(`#q${el.id}_no`).prop('checked', !isChecked);
            });

            $('#isScrutiny_yes').prop('checked', comments[0].isScrutiny);
            $('#isScrutiny_no').prop('checked', !comments[0].isScrutiny);
            $('#comments').val(comments[0].comments || '');
            $('#dateCompleted').val(comments[0].dateCompleted || '');
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}

// cancel routes back to previous screen
cancelEvent = () => {
    window.history.back();
}

//populate the table row with elements from the above array of objects
$(document).ready(function () {
    const tableBody = $("#assessmentTable tbody");

    tablePopulationList.forEach((val, index) => {
        const row = $("<tr>").html(`
                <td class="td_index">${index + 1}</td>
                <td>${val.desc}</td>
                <td><input type="checkbox" class="yes-checkbox" id="q${val.id}_yes" name="q${index + 1}_yes"></td>
                <td><input type="checkbox" class="no-checkbox" id="q${val.id}_no" name="q${index + 1}_no"></td>`);

        tableBody.append(row);
    });

    $('.yes-checkbox').change(function () {
        toggleChangeButton(this, 'no-checkbox');
    });

    $('.no-checkbox').change(function () {
        toggleChangeButton(this, 'yes-checkbox');
    });
});

// fucntion to toggle between yes and no buttons
toggleChangeButton = ($el, toggleEl) => {
    const checkboxEl = $($el).closest('tr').find(`.${toggleEl}`);

    if ($($el).is(':checked')) {
        checkboxEl.prop('checked', false)
    }
}

// submit form fucntion to save and update the data
submitForm = () => {
    const commentsData = {};

    tablePopulationList.forEach(el => {
        commentsData[el.id] = $(`#q${el.id}_yes`).is(':checked');
    });

    commentsData["isScrutiny"] = $('#isScrutiny_yes').is(':checked');
    commentsData["comments"] = $('#comments').val();
    commentsData["dateCompleted"] = $('#dateCompleted').val();
    commentsData["assessmentId"] = assessmentId;
    commentsData["modPanId"] = modPanId;

    // ajax call to save and update comnments
    $.ajax({
        type: 'POST',
        url: '/saveComments',
        contentType: 'application/json',
        data: JSON.stringify(commentsData),
        success: function () {
            alert("Save successful");

            window.history.back();
        },
        error: function (xhr, status, error) {
            console.error("Save failed. Status: " + status + ", Error: " + error);
            alert("Save failed");
        }
    });
}