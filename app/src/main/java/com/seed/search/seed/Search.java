package com.seed.search.seed;

import android.os.Bundle;

/**
 * Created by brett on 2/13/16.
 */
public class Search {
    public static Bundle[] performSearch(String query){
        /*
        call sacheths stuff
        CHANGE THIS M8
         */
       Bundle temporary[] = new Bundle[40];
        for(int a= 0;a<40;a++){
            temporary[a] = new Bundle();
            temporary[a].putInt("page",a);
            temporary[a].putString("sentence","Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            temporary[a].putString("paragraph","Praesent a aliquam lacus. Phasellus tempus est eros, in tempor erat semper a. Nunc in nulla vel leo tincidunt tempor ac nec ligula. Nunc aliquet mi eu sapien rutrum, vulputate faucibus massa scelerisque. Vivamus in neque leo. Pellentesque lacinia ligula at pellentesque euismod. Suspendisse fringilla, tellus at ultrices finibus, dolor dui egestas odio, nec facilisis velit augue sed sapien. Ut lacinia velit quis arcu facilisis, vitae lobortis dui commodo. Phasellus in urna at lorem feugiat gravida.");
        }
        return temporary;
    }

}
