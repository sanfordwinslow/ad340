package com.san.agster;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)

public class LoginSharedPreference {
    private MainActivity mainActivity = new MainActivity();

    final SharedPreferences sharedPreferences = Mockito.mock(SharedPreferences.class);

    @Mock
    SharedPreferences mockPreferences;
    @Mock
    SharedPreferences.Editor mockEditor;

    private mockedSharedPreferences mockHelper;
    private  String input = "text";



    @Before
    public void initMocks() {
        mockHelper = createMockedSharedPreferences();
    }

    @Test
    public void validateInputStoringPreferences() {
        boolean success = mockHelper.saveEntry(input);
        assertThat("SharedPreferenceEntry.save returns true", success, is(true));
    }

    @Test
    public void validateInputRetrievesFromPreferences() {
        assertEquals(input, mockHelper.getEntry());
    }

    private mockedSharedPreferences createMockedSharedPreferences() {
        when(mockPreferences.getString(eq("input"), anyString())).thenReturn(input);
        when(mockEditor.commit()).thenReturn(true);
        when(mockPreferences.edit()).thenReturn(mockEditor);
        return new mockedSharedPreferences(mockPreferences);
    }
}
