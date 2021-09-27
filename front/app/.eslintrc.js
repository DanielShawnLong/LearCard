module.exports = {
  'env': {
    'browser': true,
    'es2021': true
  },
  'extends': [
    'eslint:recommended',
    'plugin:react/recommended'
  ],
  'parserOptions': {
    'ecmaFeatures': {
      'jsx': true
    },
    'ecmaVersion': 12,
    'sourceType': 'module'
  },
  'plugins': [
    'react'
  ],
  'rules': {
    'react/jsx-filename-extension': [1, {'extensions': ['.js']}],
    'react/jsx-no-duplicate-props': [1, { 'ignoreCase': false }],
    'react/prop-types': 'off',
    'semi': [2, 'never'],
    'indent': [2, 2], 
    'no-multiple-empty-lines': ['error', { 'max': 1, 'maxEOF': 0 }],
    'no-unused-vars':'off',
    'no-unescaped-entities': 0,
    'quotes': [2,'single',
      {
        'avoidEscape': true
      }
    ]
  },
}
