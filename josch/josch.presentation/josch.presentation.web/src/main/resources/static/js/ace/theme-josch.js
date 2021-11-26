define("ace/theme/josch",["require","exports","module","ace/lib/dom"], function(require, exports, module) {

    exports.isDark = false;
    exports.cssClass = "ace-josch";
    exports.cssText = ":root {\n" +
        "    --ace-orange: #fd7e14;\n" +
        "    --ace-cyan: rgb(181, 213, 255);\n" +
        "    --ace-white: #ffffff;\n" +
        "    --ace-black: #000;\n" +
        "    --ace-gray: #555651;\n" +
        "    --ace-gray-light: #8F908A;\n" +
        "    --ace-primary: #02549c;\n" +
        "    --ace-secondary: #026FCF;\n" +
        "    --ace-info: #525252;\n" +
        "    --ace-blue-light: #f5f5f5;\n" +
        "    --ace-light: #F7F7F7;\n" +
        "}\n" +
        "\n" +
        ".ace-josch  {\n" +
        "    background: var(--ace-white);\n" +
        "    color: var(--ace-black);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_gutter {\n" +
        "    background: var(--ace-light);\n" +
        "    color: var(--ace-info);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_gutter-active-line,\n" +
        ".ace-josch .ace_gutter-cell .ace_gutter-active-line {\n" +
        "    background-color : var(--ace-blue-light);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_keyword {\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_string,\n" +
        ".ace-josch .ace_string.ace_regexp {\n" +
        "    color: var(--ace-primary);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_variable.ace_class,\n" +
        ".ace-josch .ace_constant.ace_numeric,\n" +
        ".ace-josch .ace_constant.ace_buildin,\n" +
        ".ace-josch .ace_support.ace_function,\n" +
        ".ace-josch .ace_variable.ace_language,\n" +
        ".ace-josch .ace_variable.ace_instance\n" +
        "{\n" +
        "    color: var(--ace-secondary);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_comment {\n" +
        "    color: var(--ace-gray);\n" +
        "    font-style: italic;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_paren {\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_boolean {\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_constant.ace_language {\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_cursor {\n" +
        "    color: var(--ace-black);\n" +
        "}\n" +
        "\n" +
        ".ace-josch.ace_focus .ace_marker-layer .ace_active-line {\n" +
        "    background: var(--ace-blue-light);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_active-line {\n" +
        "    background: var(--ace-light);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_selection {\n" +
        "    background: var(--ace-cyan);\n" +
        "}\n" +
        "\n" +
        ".ace-josch.ace_multiselect .ace_selection.ace_start {\n" +
        "    box-shadow: 0 0 3px 0 var(--ace-white);\n" +
        "}\n" +
        "\n" +
        ".ace-josch.ace_nobold .ace_line > span {\n" +
        "    font-weight: normal !important;\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_step {\n" +
        "    background: var(--ace-orange);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_stack {\n" +
        "    background: rgb(164, 229, 101);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_bracket {\n" +
        "    margin: -1px 0 0 -1px;\n" +
        "    border: 1px solid var(--ace-light);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_marker-layer .ace_selected-word {\n" +
        "    background: var(--ace-light);\n" +
        "    border: 1px solid var(--ace-cyan);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_invisible {\n" +
        "    color: var(--ace-gray-light)\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_print-margin {\n" +
        "    width: 1px;\n" +
        "    background: var(--ace-gray-light);\n" +
        "}\n" +
        "\n" +
        ".ace-josch .ace_indent-guide {\n" +
        "    background: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAACCAYAAACZgbYnAAAAE0lEQVQImWP4////f4bLly//BwAmVgd1/w11/gAAAABJRU5ErkJggg==\") right repeat-y;\n" +
        "}";

    const dom = require("../lib/dom");
    dom.importCssString(exports.cssText, exports.cssClass);
});

(function() {
    window.require(["ace/theme/josch"], function(m) {
        if (typeof module == "object" && typeof exports == "object" && module) {
            module.exports = m;
        }
    });
})();