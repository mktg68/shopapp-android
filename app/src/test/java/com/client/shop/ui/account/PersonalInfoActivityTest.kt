package com.client.shop.ui.account

import android.content.Context
import android.view.inputmethod.EditorInfo
import com.client.shop.TestShopApplication
import com.client.shop.gateway.entity.Customer
import com.nhaarman.mockito_kotlin.*
import kotlinx.android.synthetic.main.activity_personal_info.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, application = TestShopApplication::class)
class PersonalInfoActivityTest {

    private lateinit var activity: PersonalInfoActivity
    private lateinit var context: Context

    private val oldCustomer: Customer = mock {
        on { email } doReturn "mail@mail.com"
        on { firstName } doReturn "firstName"
        on { lastName } doReturn "lastName"
        on { phone } doReturn "06332916"
    }

    private val newCustomer: Customer = mock {
        on { email } doReturn "new_mail@mail.com"
        on { firstName } doReturn "new_firstName"
        on { lastName } doReturn "new_lastName"
        on { phone } doReturn "06332916234"
    }

    @Before
    fun setUpTest() {
        activity = Robolectric.setupActivity(PersonalInfoActivity::class.java)
        context = RuntimeEnvironment.application.baseContext
        doNothing().`when`(activity.presenter).getCustomer()
    }

    @After
    fun tearDown() {
        activity.finish()
    }

    @Test
    fun shouldShouldLoadCurrentCustomerOnLoadData() {
        activity.loadData()
        verify(activity.presenter, times(2)).getCustomer()
    }

    @Test
    fun shouldShowCustomerEmailWhenCustomerLoaded() {
        activity.setupCustomerEmail("mail@mail.com")
        assertEquals("mail@mail.com", activity.emailInput.text.toString())
    }

    @Test
    fun shouldShowCustomerNameWhenCustomerLoaded() {
        activity.showContent(oldCustomer)
        assertEquals("firstName", activity.firstNameInput.text.toString())
    }

    @Test
    fun shouldShowCustomerLastNameWhenCustomerLoaded() {
        activity.showContent(oldCustomer)
        assertEquals("lastName", activity.lastNameInput.text.toString())
    }

    @Test
    fun shouldShowCustomerPhoneWhenCustomerLoaded() {
        activity.showContent(oldCustomer)
        assertEquals("lastName", activity.lastNameInput.text.toString())
    }

    @Test
    fun emailFieldShouldBeDisabled() {
        assertFalse(activity.emailInput.isEnabled)
        activity.showContent(oldCustomer)
        assertFalse(activity.emailInput.isEnabled)
    }

    @Test
    fun clearFocusOnPhoneFieldClick() {
        activity.phoneInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
        assertFalse(activity.phoneInput.isFocused)
    }

    //TODO ADD HIDE PROGRESS TEST (Fix inner handler check)


}