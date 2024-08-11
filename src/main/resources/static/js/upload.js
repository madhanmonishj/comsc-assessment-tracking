document.addEventListener("DOMContentLoaded", function () {
    // Ajax request to fetch data from the backend
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/fetchAssessments", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var assessments = JSON.parse(xhr.responseText);

            // Populate the dropdown with assessment names
            var select = document.getElementById("assessmentSelect");
            assessments.forEach(function (assessment) {
                var option = document.createElement("option");
                option.value = assessment.assessment_id;  // Change to assessment_id
                option.text = assessment.module_name;  // Change to module_name
                select.appendChild(option);
            });
        }
    };
    xhr.send();
});

function uploadFile() {
    var fileInput = document.getElementById('fileInput');
    var file = fileInput.files[0];
    var assessmentSelect = document.getElementById('assessmentSelect');
    var selectedAssessmentId = assessmentSelect.value;

    var formData = new FormData();
    formData.append('file', file);
    formData.append('assessmentId', selectedAssessmentId);

    fetch('http://localhost:8080/file/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            alert("Assessment Updated successfully");
        })
        .catch(error => console.error('Error:', error));
}