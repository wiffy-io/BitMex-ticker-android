package io.wiffy.bitmexticker.ui.information.detailsFragment

class DetailsPresenter(val mView: DetailsContract.View) : DetailsContract.Presenter {
    override fun init() = mView.changeUI()
}