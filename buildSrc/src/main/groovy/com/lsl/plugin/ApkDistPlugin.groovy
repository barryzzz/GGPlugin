package com.lsl.plugin

import com.lsl.plugin.task.ApkReleaseTask
import com.lsl.plugin.task.ApkbuildTask
import org.gradle.api.tasks.Delete

class ApkDistPlugin extends BasePlugin {


    @Override
    protected void createExtension() {
        super.createExtension()
        project.extensions.create("apkdistconf", ApkDistExtension.class)
    }

    @Override
    protected void configProject() {
        super.configProject()
    }

    @Override
    protected void createTask() {
        super.createTask()


        project.task('apkbuild', group: 'apk', type: ApkbuildTask,
                description: 'apkbuild')
        project.task('apkclean', group: 'apk', type: Delete,
                description: 'apkclean') {
            delete new File(project.buildDir, "xxxxxx")
        }
        project.task('apkRelease', group: 'apk', type: ApkReleaseTask,
                description: 'apkRelease')


    }
}
