package ru.jerold.showcaseview_v2.bubbleshowcase

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
}