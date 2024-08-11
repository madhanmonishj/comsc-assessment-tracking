// Extract user ID from the Url query parameters or default to 0
const userID = decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0;

$(document).ready(function () {

    // Fetch user data if it is an existing record
    if (userID) {
        $('#header').text('Edit User');

        // Ajax call to fetch user details
        $.ajax({
            type: 'GET',
            url: '/Users/getUser',
            contentType: 'application/json',
            data: { userID },
            success: function (user) {
                // Destructure user data and added fallback value
                const {
                    firstName,
                    lastName,
                    dateOfBirth,
                    assignedRoles,
                    email,
                    userName
                } = user[0] || {};

                // Populate form fields with user data
                $('#firstName').val(firstName);
                $('#lastName').val(lastName);
                $('#userName').val(userName);
                $('#dob').val(formattdDateOfBirth(dateOfBirth));
                $('#multiselect').select2({
                    placeholder: 'Select Modules',
                    allowClear: true,
                    width: '80%',
                    data: assignedRoles?.split(',') || []
                });
                $('#emailId').val(email);
            }
        });
    } else {
        // Initialize  multiselect field
        $('#multiselect').select2({
            placeholder: 'Select Modules',
            allowClear: true,
            width: '80%'
        });

        $('#header').text('Add User');
    }
});

// Function to format date of birth to 'dd-mm-yyyy'
formattdDateOfBirth = (date) => {
    const newDate = date.split('-');
    return `${newDate[0]}-${newDate[1]}-${newDate[2]}`;
}

window.backForm = () => {
    window.history.back();
}

// Function to submit the user form data
submitForm = () => {
    // Prepare user data from form fields
    const userData = {
        id: userID,
        email: $('#emailId').val(),
        role: 1,
        userName: $('#userName').val(),
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        dateOfBirth: $('#dob').val(),
        assignedRoles: $('#multiselect').val().join(','),
    };

    // Ajax call to save or update user data
    $.ajax({
        type: 'POST',
        url: '/Users/saveUser',
        contentType: 'application/json',
        data: JSON.stringify(userData),
        success: function () {
            alert("Save successful");

            // Navigate back to the previous page
            window.history.back();
        },
        error: function (xhr, status, error) {
            console.error("Save failed. Status: " + status + ", Error: " + error);
            alert("Save failed");
        }
    });
}
