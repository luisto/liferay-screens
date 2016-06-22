package com.liferay.mobile.screens.rating.interactor.delete;

import android.support.annotation.NonNull;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.ratingsentry.RatingsEntryService;
import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.rating.RatingListener;
import com.liferay.mobile.screens.rating.interactor.RatingEntryFactory;
import com.liferay.mobile.screens.util.LiferayLogger;
import java.security.InvalidParameterException;
import org.json.JSONException;

/**
 * @author Alejandro Hernández
 */
public class DeleteRatingInteractorImpl extends BaseRemoteInteractor<RatingListener> implements
    DeleteRatingInteractor {
  public DeleteRatingInteractorImpl(int targetScreenletId) {
    super(targetScreenletId);
    _ratingsEntryService = getRatingsEntryService();
  }

  @Override public void deleteRating(String className, long classPK)
      throws Exception {
    _ratingsEntryService.deleteEntry(className, classPK);
  }

  @NonNull private RatingsEntryService getRatingsEntryService() {
    Session session = SessionContext.createSessionFromCurrentSession();
    session.setCallback(new DeleteRatingCallback(getTargetScreenletId()));
    return new RatingsEntryService(session);
  }

  public void onEvent(DeleteRatingEvent event) {
    if (!isValidEvent(event)) {
      return;
    }

    if (event.isFailed()) {
      getListener().onDeleteRatingEntryFailure(event.getException());
    } else {
     getListener().onDeleteRatingEntrySuccess();
    }
  }

  private final RatingsEntryService _ratingsEntryService;
}
