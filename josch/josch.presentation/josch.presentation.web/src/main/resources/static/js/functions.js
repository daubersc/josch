/**
 * Sets the editor text and renders the page.
 *
 * @param side The side to render.
 * @param text The text to set.
 */
const renderSelection = (side, text) => {
    // Render the editor.
    let editor = ace.edit(side + '-editor');
    editor.setValue(text, 1);

    // Get the elements.
    let select = document.getElementById(side + '-slc');
    let nextButton = document.getElementById(side + '-next-btn');
    let backButton = document.getElementById(side + '-back-btn');

    // Get the selected index and determine whether there are next and previous.
    let i = select.selectedIndex;
    let hasPrevious = i > 1;
    let hasNext = i < select.length - 1;

    // disable the buttons.
    nextButton.disabled = !hasNext;
    backButton.disabled = !hasPrevious;
}
