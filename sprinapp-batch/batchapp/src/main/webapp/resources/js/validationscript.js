//
//	jQuery Validate example script
//
//	Prepared by David Cochran
//
//	Free for your use -- No warranties, no guarantees!
//

$(document).ready(function(){

    $('#amortizationFrom').validate(
        {
            rules: {
                loanAmount: {

                    required: true
                },
                loanAPR: {
                    required: true

                },
                loanTerm: {

                    required: true
                }
            },
            highlight: function(element) {
                $(element).closest('.control-group').removeClass('success').addClass('error');
            },
            success: function(element) {
                element
                    .text('OK!').addClass('valid')
                    .closest('.control-group').removeClass('error').addClass('success');
            }
        });

}); // end document.ready