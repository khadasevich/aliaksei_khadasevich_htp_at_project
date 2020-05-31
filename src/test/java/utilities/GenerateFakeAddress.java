package utilities;

public class GenerateFakeAddress {

    public static String generateEmail() {
        String preparedString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(6);
        for (int j = 0; j < 6; j++) {
            int index
                    = (int) (preparedString.length()
                    * Math.random());
            sb.append(preparedString
                    .charAt(index));
        }
        sb.append("@yopmail.com");
        return sb.toString();
    }

}
