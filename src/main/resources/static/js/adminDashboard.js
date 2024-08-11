import { groupAssessmentsByModule } from './utils.js';

window.changeForm = (id) => {
    window.location.href = `http://localhost:8080/admin/assessment?id=${id}`;
}


const getAssementGrid = (moduleCode, assessments) => {
    const moduleDiv = $('<div>').attr('id', `moduleGrid_${moduleCode}`).addClass('ag-theme-alpine assessGrid');

    moduleDiv.append('<h3/>');
    moduleDiv.find('h3').text(`Module: ${moduleCode} - ${assessments[0].module_name}`);
    $("#dashboardGrid").append(moduleDiv);

    var gridOptions = {
        columnDefs: [
            // Define columns for the grid 
            { headerName: 'ID', field: 'tracker_id', width:80 },
            { headerName: 'Assessment', field: 'assessment_name'},
            { headerName: 'Uploaded File', field: 'uploaded_assessment_name'},
            { headerName: 'Status', field: 'assessment_status' },
            {
                // Define a custom column for "Actions" with an View button
                headerName: 'Actions',
                cellRenderer: (params) => {
                    // Edit button with a call to changeForm function
                    return '<button type="button" class="btn btn-primary" onclick="changeForm(' + params.node.data.tracker_id + ')"><span class="bi bi-pencil"></span> View </button>';
                },
                width: 120,
                suppressSorting: true,
                suppressFilter: true,
            }
        ],

        // Populate the grid with fetched user data
        rowData: assessments,
    };

    // Initaite the  ag-Grid using the specified options
    new agGrid.Grid(document.getElementById(`moduleGrid_${moduleCode}`), gridOptions);
}

const fetchAssessments  = () => {
    $.ajax({
        url: '/tracking/lists',
        type: 'GET',
        dataType: 'json',
        success: function (dataList) {
            const groupedData = groupAssessmentsByModule(dataList);

            for (const moduleCode in groupedData) {
                if (groupedData.hasOwnProperty(moduleCode)) {
                    const assessments = groupedData[moduleCode];
                    getAssementGrid(moduleCode, assessments);
                }
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error('Error fetching JSON data:', textStatus, errorThrown);
        }
    });
}

$(document).ready( () => {
    fetchAssessments();
});
