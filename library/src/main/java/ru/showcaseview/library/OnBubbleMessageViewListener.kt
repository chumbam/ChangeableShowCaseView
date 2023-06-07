package ru.showcaseview.library

/**
 * Created by jcampos on 10/09/2018.
 * Modified by chumbam on 06/06/2023
 */
interface OnBubbleMessageViewListener {
    /**
     * It is called when a user clicks the close action image in the BubbleMessageView
     */
    fun onCloseActionImageClick()


    /**
     * It is called when a user clicks the BubbleMessageView
     */
    fun onBubbleClick()

    /**
     * It is called when a user clicks the ActionButton first image
     */
    fun onActionClick()
}