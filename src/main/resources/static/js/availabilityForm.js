function saveData(e) {
    e.preventDefault();

    // form data to be aprsed to json
    let receivedData = decodeURIComponent(new URLSearchParams(window.location.search).get("id"));
    let avl_id = decodeURIComponent(new URLSearchParams(window.location.search).get("avl_id"));
    let ifNotified = $('input[name="Notified"]').prop('checked');
    let ifChecked = $('input[name="Checked"]').prop('checked');
    let date = $('#date').val();
    let times = $('#times').val();
    let stuNum = $('#stuNum').val();
    let markDates = $('#markDates').val();
    let deadline = $('#deadline').val();
    let summary = $('#summary').val();

    // Cleatr the forms after submit
    function clearFormFields() {
        $('input[name="Notified"]').prop('checked', false);
        $('input[name="Checked"]').prop('checked', false);
        $('#date').val('');
        $('#times').val('');
        $('#stuNum').val('');
        $('#markDates').val('');
        $('#deadline').val('');
        $('#summary').val('');
    }

    let data = {
        notified: ifNotified,
        checked: ifChecked,
        date: date,
        times: times,
        stuNum: stuNum,
        markDates: markDates,
        deadline: deadline,
        summary: summary,
        assessmentID: receivedData,
        avlID: avl_id
    };
    if (date === '' && (times !== '' || stuNum !== '' || markDates !== '' || deadline !== '')) {
        alert('Date field cannot be empty');
    } else if ((times === '' || stuNum === '' || markDates === '' || deadline === '') && date !== '') {
        alert('Tracking Plan Details cannot be empty');
    } else if (date === '' && (times === '' || stuNum === '' || markDates === '' || deadline === '')) {
        alert('Data and Tracking Plan Details cannot be empty');
    } else {
        $.ajax({
            type: 'POST',
            url: '/public/saveData',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                alert("Save successful");
                clearFormFields();
                window.history.back();
            },
            error: function (xhr, status, error) {
                console.error("Save failed. Status: " + status + ", Error: " + error);
                alert("Save failed");
            }
        });

    }
    
}

const assessmentId = decodeURIComponent(new URLSearchParams(window.location.search).get("avl_id")) || 0;

if(assessmentId){
    $.ajax({
        type: 'GET',
        url: '/public/getAvailabilityForm',
        contentType: 'application/json',
        data: { assessmentId },
        success: function (form){
            getDataIntoCheckboxAndInput(form);
        },
        error: function (error){
            console.error('Error fetching data:', error);
        }
    });
}
function getDataIntoCheckboxAndInput(form){
    $('[name="Notified"]').prop('checked',form[0].ifNotified === true);
    $('[name="NotNotified"]').prop('checked',form[0].ifNotified !== true);
    $('[name="Checked"]').prop('checked',form[0].ifChecked === true);
    $('[name="unChecked"]').prop('checked',form[0].ifChecked !== true);
    $('[name="date"]').val(form[0].date || '');
    $('[name="markingTimes"]').val(form[0].times || '');
    $('[name="stuNum"]').val(form[0].stuNum || '');
    $('[name="MarkDates"]').val(form[0].markDates || '');
    $('[name="Deadline"]').val(form[0].deadline || '');
    $('[name="summaryChanges"]').val(form[0].summary || '');
}