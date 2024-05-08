package prom.application.mynotes.ui

import prom.application.mynotes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import prom.application.mynotes.core.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)