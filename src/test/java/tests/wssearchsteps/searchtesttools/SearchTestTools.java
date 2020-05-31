package tests.wssearchsteps.searchtesttools;

import testobjects.ResponseBodyJsonObject;
import testobjects.UserJsonObject;

import java.util.List;

public class SearchTestTools {

    public static boolean responseChecker(ResponseBodyJsonObject actual, ResponseBodyJsonObject expected) {
        List<UserJsonObject> actualUsers = actual.getData();
        List<UserJsonObject> expectedUsers = expected.getData();
        int flag = 0;
        for (int i = 0; i < actualUsers.size(); i++) {
            if (!actualUsers.get(i).equals(expectedUsers.get(i))) {
                System.out.println("Check users:\n");
                System.out.println("User Id:" + expectedUsers.get(i).getId() + "\n");
                System.out.println("Expect User: " + expectedUsers.get(i).toString() + "\n");
                System.out.println("Actual User: " + actualUsers.get(i).toString() + "\n");
                flag = 1;
            }
        }
        if (flag == 0) {
            return true;
        } else {
            return false;
        }
    }
}
