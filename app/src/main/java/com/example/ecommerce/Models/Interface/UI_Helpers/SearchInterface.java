package com.example.ecommerce.Models.Interface.UI_Helpers;

import com.example.ecommerce.Models.DataTypes.Search;

public interface SearchInterface {
    void onPhraseSelected(Search search);
    void onError(Throwable throwable);
}
