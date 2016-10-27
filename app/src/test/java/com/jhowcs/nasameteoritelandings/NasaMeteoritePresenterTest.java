package com.jhowcs.nasameteoritelandings;

import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.presenter.NasaMeteoritePresenter;
import com.jhowcs.nasameteoritelandings.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import java.util.List;

import retrofit2.Response;

/**
 * Created by jonathan_campos on 27/10/2016.
 */

public class NasaMeteoritePresenterTest {

    @Mock
    MainView mMainView;

    @Captor
    private ArgumentCaptor<Response<List<NasaMeteoriteLandings>>> mResponseArgumentCaptor;

    private NasaMeteoritePresenter mPresenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new NasaMeteoritePresenter(mMainView);
    }

    @Test
    public void loadMeteoritesAndLoadIntoView() {
        mPresenter.loadMeteoriteList();

        verify(mPresenter).loadMeteoriteList();

        verify(mMainView).onShowProgress();
    }
}
