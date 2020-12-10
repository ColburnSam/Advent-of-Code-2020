import static java.lang.Integer.parseInt;

public class Password {

    private int number1;
    private int number2;
    private char letter;
    private String password;

    // Generates a password object from a line of the input file.
    public Password(String line){
        this.number1 = parseInt(line.substring(0, line.indexOf('-')));
        this.number2 = parseInt(line.substring(line.indexOf('-') + 1, line.indexOf(':') - 2));
        this.letter = line.charAt(line.indexOf(' ') + 1);
        this.password = (line.substring(line.indexOf(':') + 1).trim());
    }

    // Determines if the password is valid under the given rule number (rule 1 or rule 2)
    // Returns true if password follows the given rule, false if it does not
    // Returns false if rule number is out of bounds
    public boolean passwordIsValid(int ruleNumber){
        switch (ruleNumber) {
            case 1:
                return passwordIsValid1();
            case 2:
                return passwordIsValid2();
            default:
                return false;
        }
    }

    // Returns true if the password follows rule 1
    private boolean passwordIsValid1(){
        int charCount = 0;
        for (int i = 0; i < this.password.length(); i++){
            if (this.password.charAt(i) == this.letter){
                charCount ++;
            }
        }
        return charCount >= this.number1 && charCount <= this.number2;
    }

    // Returns true if the password follows rule 2
    private boolean passwordIsValid2(){
        return password.charAt(this.number1-1) == this.letter ^
                password.charAt(this.number2-1) == letter;
    }

}
