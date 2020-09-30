package DataProviders;

import org.testng.annotations.DataProvider;

public class LoginDP {

    @DataProvider(name="LoginData")
    public static Object[][] dataProviderLogin(){
        return new Object[][]{
                {"testingproject444@gmail.com","testing-1234"}
                //{"chrome","https://www.ajio.com/login","testingproject444@gmail.com","testing-1234"}
            };
        }
    }

