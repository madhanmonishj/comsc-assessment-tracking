$(document).ready(function () {
    // AJAX call to fetch user data from the server
    $.ajax({
        url: '/Users/lists',
        type: 'GET',
        dataType: 'json',
        success: function (jsonData) {
            // Configuring ag-Grid options
            var gridOptions = {
                columnDefs: [
                    // Define columns for the grid 
                    { headerName: 'ID', field: 'id', width: 100 },
                    { headerName: 'Username', field: 'userName', width: 200 },
                    { headerName: 'Email', field: 'email', width: 200 },
                    { headerName: 'Role', field: 'assignedRoles', width: 150 },
                    {
                        // Define a custom column for "Actions" with an Edit button
                        headerName: 'Actions',
                        cellRenderer: (params) => {
                            // Edit button with a call to changeForm function
                            return '<button type="button" class="btn btn-primary" onclick="changeForm(' + params.node.data.id + ')"><span class="bi bi-pencil"></span> Edit</button>';
                        },
                        width: 120,
                        suppressSorting: true,
                        suppressFilter: true,
                    }
                ],

                // Populate the grid with fetched user data
                rowData: jsonData, 
            };

            // Initaite the  ag-Grid using the specified options
            new agGrid.Grid(document.getElementById('usersGrid'), gridOptions);
        }
    });
});

// Function to navigate to the user edit view with a specified user ID
changeForm = (id) => {
    window.location.href = `http://localhost:8080/Users/userview?id=${id || 0}`;
};
