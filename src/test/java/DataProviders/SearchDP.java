package DataProviders;

import org.testng.annotations.DataProvider;

public class SearchDP {
    @DataProvider(name="SearchProduct")
    public static Object[][] dataProviderSearch(){
        return new Object[][]{{"testingproject444@gmail.com","testing-1234","watches"}};
    }
}
