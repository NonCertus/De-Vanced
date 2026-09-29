/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/layout/HidePremiumUpgradeButton.kt
 */
package app.morphe.patches.gmxmail.layout

import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.util.returnEarly
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions

@Suppress("unused")
val hidePremiumUpgradeButtonPatch = bytecodePatch(
    name = "Hide Premium upgrade button",
    description = "Hides the Premium upgrade button in the navigation drawer.",
) {
    compatibleWith(AppCompatibilities.GMX_MAIL)

    execute {
        if (packageMetadata.versionName == "9.17.2") {
            IsUpsellingPossibleFingerprint.method.returnEarly(false)

            val moveResult = NavigationDrawerDisplayFingerprint.instructionMatches[3]
            val register = moveResult
                .getInstruction<OneRegisterInstruction>()
                .registerA

            NavigationDrawerDisplayFingerprint.method.addInstructions(
                moveResult.index + 1,
                "const/4 v$register, 0x0",
            )
        }
    }
}
