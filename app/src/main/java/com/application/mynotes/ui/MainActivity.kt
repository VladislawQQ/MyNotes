package com.application.mynotes.ui

import com.application.mynotes.databinding.ActivityMainBinding
import com.application.mynotes.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)