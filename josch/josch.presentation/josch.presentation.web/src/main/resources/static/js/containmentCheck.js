/** Process the containment check using Ajax. */
$('#check-btn').click(function () {

    // Prepare the check Request (@see .utils.CheckRequest.java for reference)
    const checkRequest = {};
    checkRequest["s1"] = $("#s1").val();
    checkRequest["s2"] = $("#s2").val();
    checkRequest["tool"] = $("#containment-tool-slc").val();

    // Send a POST request to the controller to process the data without interferring with the page.
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "processContainment",
        data: JSON.stringify(checkRequest),
        dataType: 'json',

        // Clear previous information and disable the button while waiting for the response.
        beforeSend: function () {
            // clear Message and set default icon.
            $("#containment-info-txt").html("");
            $("#contain-pth").removeClass();
            $("#contain-pth").addClass("pc-primary pi-is-eqv");

            // disable check button.
            $("#check-btn").prop("disabled", true);
        },


        // On successful callback update the information.
        success: function (data) {
            $("#containment-info-txt").html(data.message);
            $("#contain-pth").removeClass();
            $("#contain-pth").addClass(data.icon);
        },

        // request finished, independently whether successfully or not.
        complete: function () {
            $("#check-btn").prop("disabled", false);
        }
    })
})