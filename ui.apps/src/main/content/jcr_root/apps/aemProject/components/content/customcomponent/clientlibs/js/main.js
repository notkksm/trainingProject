(function($) {
    const SELECTOR = "project.validation",
    foundationReg = $(window).adaptTo("foundation-registry");
    foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation='" + SELECTOR + "']",
        validate: function(el) {
            let regex = /^[A-Za-z]+$/;
            let message = "Title must contain only letters";
            var result = el.value.match(regex);
            if (result === null) {
                return message;
            }
        }
    });
}(jQuery));
