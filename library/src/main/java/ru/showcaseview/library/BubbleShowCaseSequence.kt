package ru.showcaseview.library

/**
 * Created by jcampos on 10/09/2018.
 * Modified by chumbam on 06/06/2023
 */
class BubbleShowCaseSequence{
    private val mBubbleShowCaseBuilderList = ArrayList<BubbleShowCaseBuilder>()

    init{
        mBubbleShowCaseBuilderList.clear()
    }

    fun addShowCase(bubbleShowCaseBuilder: BubbleShowCaseBuilder): BubbleShowCaseSequence {
        mBubbleShowCaseBuilderList.add(bubbleShowCaseBuilder)
        return this
    }

    fun addShowCases(bubbleShowCaseBuilderList: List<BubbleShowCaseBuilder>): BubbleShowCaseSequence {
        mBubbleShowCaseBuilderList.addAll(bubbleShowCaseBuilderList)
        return this
    }

    fun show() = show(0)

    private fun show(position: Int){
        if(position >= mBubbleShowCaseBuilderList.size)
            return

        when(position){
            0 -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(true)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(false)
            }
            mBubbleShowCaseBuilderList.size-1 -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(false)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(true)
            }
            else -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(false)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(false)
            }
        }
        mBubbleShowCaseBuilderList[position].sequenceListener(object : SequenceShowCaseListener {
            override fun onDismiss() {
                show(position + 1)
            }
        }).show()
    }

}