package com.baidu.test.accessbilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class ClickAccessibilityService extends AccessibilityService {
    private static final String TAG = "ClickAccessibilityService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                // 捕获到点击事件
                Log.i(TAG, "capture click event!");
                AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                if (nodeInfo != null) {
                    // 查找text为Test!的控件
                    List<AccessibilityNodeInfo> button = nodeInfo.findAccessibilityNodeInfosByText("BUTTON");
                    nodeInfo.recycle();
                    for (AccessibilityNodeInfo item : button) {
                        Log.i(TAG, "long-click button!");
                        // 执行长按操作
                        item.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
                    }
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onInterrupt() {
        Log.i(TAG, "onInterrupt");
    }
}