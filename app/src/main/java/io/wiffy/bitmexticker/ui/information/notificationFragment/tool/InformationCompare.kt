package io.wiffy.bitmexticker.ui.information.notificationFragment.tool

import java.util.Comparator

class InformationComare: Comparator<NotificationInfo> {
    override fun compare(o1: NotificationInfo?, o2: NotificationInfo?): Int {
        return (o1?.value?.toDouble()?:0.0).compareTo(o2?.value?.toDouble()?:0.0)
    }
}