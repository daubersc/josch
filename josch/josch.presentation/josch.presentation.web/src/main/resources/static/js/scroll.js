/** this file implements the scrolling buttons. */

// S1 next button.
$("#s1-next-btn").click(function () {
    $("#s1-slc option:selected").next().prop("selected", "selected");
    $("#s1-slc").trigger("change");
})

// S1 previous button.
$("#s1-back-btn").click(function () {
    $("#s1-slc option:selected").prev().prop("selected", "selected");
    $("#s1-slc").trigger("change");
})

// S2 next button.
$("#s2-next-btn").click(function () {
    $("#s2-slc option:selected").next().prop("selected", "selected");
    $("#s2-slc").trigger("change");
})

// S2 previous button.
$("#s2-back-btn").click(function () {
    $("#s2-slc option:selected").prev().prop("selected", "selected");
    $("#s2-slc").trigger("change");
})

