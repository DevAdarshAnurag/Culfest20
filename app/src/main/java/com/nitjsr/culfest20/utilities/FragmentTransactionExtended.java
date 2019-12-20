package com.nitjsr.culfest20.utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;

import com.nitjsr.culfest20.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentTransactionExtended implements FragmentManager.OnBackStackChangedListener {
    public static final int GLIDE = 10;
    public static final int SLIDING = 11;
    private boolean mDidSlideOut = false;
    private boolean mIsAnimating = false;
    private FragmentTransaction mFragmentTransaction;
    private Context mContext;
    private Fragment mFirstFragment, mSecondFragment;
    private int mContainerID;
    private int mTransitionType;

    public FragmentTransactionExtended(Context context, FragmentTransaction fragmentTransaction, Fragment firstFragment, Fragment secondFragment, int containerID) {
        this.mFragmentTransaction = fragmentTransaction;
        this.mContext = context;
        this.mFirstFragment = firstFragment;
        this.mSecondFragment = secondFragment;
        this.mContainerID = containerID;
    }

    public void addTransition(int transitionType) {
        this.mTransitionType = transitionType;
        switch (transitionType) {
            case GLIDE:
                transitionGlide();
                break;
            case SLIDING:
                return;

        }
        mFragmentTransaction.replace(mContainerID, mSecondFragment);
    }

    private void transitionGlide() {
        mFragmentTransaction.setCustomAnimations(R.animator.entry_frag, R.animator.exit_frag, R.animator.entry_frag, R.animator.exit_frag);
    }

    private void switchFragments() {
        ((AppCompatActivity) this.mContext).getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (mIsAnimating) {
            return;
        }
        mIsAnimating = true;
        if (mDidSlideOut) {
            mDidSlideOut = false;
            ((AppCompatActivity) mContext).getFragmentManager().popBackStack();
        } else {
            mDidSlideOut = true;
            Animator.AnimatorListener listener = new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator arg0) {
                    mFragmentTransaction.setCustomAnimations(R.animator.slide_fragment_in, 0, 0, R.animator.slide_fragment_out);
                    mFragmentTransaction.add(mContainerID, mSecondFragment);
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                }
            };
            slideBack(listener);
        }
    }

    private void slideBack(Animator.AnimatorListener listener) {
        View movingFragmentView = mFirstFragment.getView();
        movingFragmentView.setPivotY(movingFragmentView.getHeight() / 2);
        movingFragmentView.setPivotX(movingFragmentView.getWidth() / 2);

        PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat("rotationX", 40f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.8f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.8f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.5f);
        ObjectAnimator movingFragmentAnimator = ObjectAnimator.ofPropertyValuesHolder(movingFragmentView, rotateX, scaleX, scaleY, alpha);

        ObjectAnimator movingFragmentRotator = ObjectAnimator.ofFloat(movingFragmentView, "rotationX", 0);
        movingFragmentRotator.setStartDelay(150);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(movingFragmentAnimator, movingFragmentRotator);
        s.addListener(listener);
        s.start();
    }

    private void slideForward() {
        View movingFragmentView = mFirstFragment.getView();
        PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat("rotationX", 40f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f);
        ObjectAnimator movingFragmentAnimator = ObjectAnimator.ofPropertyValuesHolder(movingFragmentView, rotateX, scaleX, scaleY, alpha);

        ObjectAnimator movingFragmentRotator = ObjectAnimator.ofFloat(movingFragmentView, "rotationX", 0);
        movingFragmentRotator.setStartDelay(150);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(movingFragmentAnimator, movingFragmentRotator);
        s.setStartDelay(200);
        s.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
                mDidSlideOut = true;
            }
        });
        s.start();
        ((AppCompatActivity) this.mContext).getSupportFragmentManager().removeOnBackStackChangedListener(this);
    }


    public void commit() {
        switch (mTransitionType) {
            case SLIDING:
                switchFragments();
                break;
            default:
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        switch (mTransitionType) {
            case SLIDING:
                if (!mDidSlideOut) {
                    slideForward();
                } else {
                    mDidSlideOut = false;
                }
                break;
        }
    }
}
