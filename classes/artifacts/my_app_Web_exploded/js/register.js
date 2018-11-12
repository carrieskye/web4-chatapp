$(async function () {
    const password = document.getElementById("password");
    const passwordRepeated = document.getElementById("passwordRepeated");

    function validatePassword() {
        console.log('com[are');
        if (password.value !== passwordRepeated.value) {
            passwordRepeated.setCustomValidity("Passwords Don't Match");
        } else {
            passwordRepeated.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    passwordRepeated.onkeyup = validatePassword;
});